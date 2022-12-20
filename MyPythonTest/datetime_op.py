#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import datetime

import pandas as pd
import pytz

"""
计算未来12个月
"""
curr_date = datetime.datetime.strptime("2020-09-22", "%Y-%m-%d")


def print_future(curr_date):
    print(curr_date)
    print(curr_date + datetime.timedelta(days=28))
    print(curr_date + datetime.timedelta(days=58))
    print(curr_date + datetime.timedelta(days=88))
    print(curr_date + datetime.timedelta(days=118))
    print(curr_date + datetime.timedelta(days=148))
    print(curr_date + datetime.timedelta(days=178))
    print(curr_date + datetime.timedelta(days=208))
    print(curr_date + datetime.timedelta(days=238))
    print(curr_date + datetime.timedelta(days=268))
    print(curr_date + datetime.timedelta(days=298))
    print(curr_date + datetime.timedelta(days=328))
print_future(datetime.datetime.strptime("2020-01-01", "%Y-%m-%d"))
print('--------------------')
print_future(datetime.datetime.strptime("2020-02-01", "%Y-%m-%d"))
print('--------------------')
print_future(datetime.datetime.strptime("2020-03-01", "%Y-%m-%d"))

exit()


# ----------------------------------------------------------------------------------------------------------------------
def date_convert(o_date_str):
    """
    出现周跨月，拆分成两段Wa,Wb 示例：
        a）周不跨月，时间段：2021-11-22到2021-11-28，聚合日期：2021-11-22
        b）周跨月，时间段：2021-11-29到2021-12-05，聚合日期：Wa=2021-11-29,Wb=2021-12-01
    """

    curr_date = datetime.datetime.strptime(o_date_str, "%Y-%m-%d")
    curr_year = curr_date.year
    curr_month = curr_date.month

    curr_gvu = curr_date.isocalendar()
    curr_g = curr_gvu[0]
    curr_v = curr_gvu[1]

    targ_g = curr_g
    targ_v = curr_v
    targ_u = 1
    targ_date_str = " ".join([str(targ_g), str(targ_v), str(targ_u)])
    targ_date = datetime.datetime.strptime(targ_date_str, "%G %V %u")
    targ_month = targ_date.month

    if curr_month == targ_month:
        return targ_date.strftime("%Y-%m-%d")

    return datetime.datetime.strptime(str(curr_year) + " " + str(curr_month), '%Y %m').strftime('%Y-%m-%d')


# assert date_convert("2020-10-30") == "2020-10-26"
# assert date_convert("2020-11-01") == "2020-11-01"
# assert date_convert("2020-12-27") == "2020-12-21"
# assert date_convert("2020-12-28") == "2020-12-28"
# assert date_convert("2020-12-31") == "2020-12-28"
# assert date_convert("2021-01-01") == "2021-01-01"
# assert date_convert("2021-01-03") == "2021-01-01"
# assert date_convert("2021-12-27") == "2021-12-27"
# assert date_convert("2021-12-31") == "2021-12-27"
# assert date_convert("2022-01-01") == "2022-01-01"
# assert date_convert("2022-01-02") == "2022-01-01"
# assert date_convert("2022-01-03") == "2022-01-03"
# ----------------------------------------------------------------------------------------------------------------------
def date_convert_last_week(o_date_str):
    """
    计算给定日期的所属周的最后一天
    """

    curr_date = datetime.datetime.strptime(o_date_str, "%Y-%m-%d")

    curr_gvu = curr_date.isocalendar()
    curr_g = curr_gvu[0]
    curr_v = curr_gvu[1]

    targ_g = curr_g
    targ_v = curr_v
    targ_u = 7
    targ_date_str = " ".join([str(targ_g), str(targ_v), str(targ_u)])
    targ_date = datetime.datetime.strptime(targ_date_str, "%G %V %u")
    return targ_date.strftime('%Y-%m-%d')


assert date_convert_last_week("2020-10-30") == "2020-11-01"
assert date_convert_last_week("2020-11-01") == "2020-11-01"
assert date_convert_last_week("2020-12-27") == "2020-12-27"
assert date_convert_last_week("2020-12-28") == "2021-01-03"
assert date_convert_last_week("2020-12-31") == "2021-01-03"
assert date_convert_last_week("2021-01-01") == "2021-01-03"
assert date_convert_last_week("2021-01-03") == "2021-01-03"
assert date_convert_last_week("2021-12-27") == "2022-01-02"
assert date_convert_last_week("2021-12-31") == "2022-01-02"
assert date_convert_last_week("2022-01-01") == "2022-01-02"
assert date_convert_last_week("2022-01-02") == "2022-01-02"
assert date_convert_last_week("2022-01-03") == "2022-01-09"
# ----------------------------------------------------------------------------------------------------------------------
# 日期减法 - 减一天
dt = datetime.datetime.now() + datetime.timedelta(days=-1)
print(datetime.datetime.strftime(dt, "%Y-%m-%d %H:%M:%S"))
# ----------------------------------------------------------------------------------------------------------------------
# 字符串转换为日期
t1 = datetime.datetime.strptime("2022-01-26 18:30:37", "%Y-%m-%d %H:%M:%S")
t2 = datetime.datetime.strptime("2022-01-26 00:00:00", "%Y-%m-%d %H:%M:%S")
print(t1)
print(t2)

# ----------------------------------------------------------------------------------------------------------------------
# 标准时间和北京时间
# +08 说的是和utc时间的偏移量，"2019-05-29T08:00:00+08:00" 这个时间的意思是utc时间加了8个小时。
# to_datetime可以适配混合模式，你给什么时间都可以，然后可以统一的用astimezone输出想要的时区。
cn_tz = pytz.timezone('Asia/Shanghai')
utc_tz = pytz.utc


def convert(x):
    t = pd.to_datetime(x, infer_datetime_format=True, errors='coerce')
    if pd.notna(t) and t.tz:
        t = t.astimezone(utc_tz)
        t = t.tz_localize(None)
        t = t + datetime.timedelta(hours=8)
        return t
    return t


df1 = pd.DataFrame({
    't': ["2022-01-24T16:00:00.000Z",
          "2019-05-29T08:00:00+08:00",
          "2022-02-10T00:00:00.000Z",
          "0000-01-01T00:00:00.000Z",
          "2022-03-14T16:10:29Z",
          "2022-03-22T16:00:00.000Z"]
})
df2 = pd.DataFrame({
    't': ["2022-01-24T16:00:00.000Z",
          "2019-05-29T08:00:00+08:00",
          "2022-02-10T00:00:00.000Z",
          "0000-01-01T00:00:00.000Z",
          "2022-03-14T16:10:29Z",
          "2022-03-22T16:00:00.000Z"]
})
df1['t'] = df1.t.apply(convert)
print('处理：\n', df1)
df3 = pd.concat([df1, df2])
print('合并：\n', df3)
df3['t'] = df3.t.apply(convert)
print('再处理：\n', df3)

# ----------------------------------------------------------------------------------------------------------------------
# 计算两个日期之间的差值，并通过差值筛选所需要的数据
df1 = pd.DataFrame({
    't1': ["2022-01-24T16:00:00.000Z",
           "2019-05-29T08:00:00+08:00",
           "2022-02-10T00:00:00.000Z",
           "0000-01-01T00:00:00.000Z",
           "2022-03-14T16:10:29Z"],
    't2': ["2022-01-25T16:00:00.000Z",
           "2019-05-29T08:00:00+08:00",
           "2022-02-09T00:00:00.000Z",
           "0000-01-01T00:00:00.000Z",
           "2022-03-14T16:10:29Z"]
})
df1 = df1.applymap(convert)
print(df1)

df1.loc[
    (df1.t1 - df1.t2).dt.days > 0,
    'yes'
] = 'yes'

print(df1)

df1['days'] = df1.t1 - df1.t2
print(df1)

print(df1[df1['days'].dt.days > 0])
# ----------------------------------------------------------------------------------------------------------------------
