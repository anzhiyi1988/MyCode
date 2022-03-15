#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import datetime


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


assert date_convert("2020-10-30") == "2020-10-26"
assert date_convert("2020-11-01") == "2020-11-01"
assert date_convert("2020-12-27") == "2020-12-21"
assert date_convert("2020-12-28") == "2020-12-28"
assert date_convert("2020-12-31") == "2020-12-28"
assert date_convert("2021-01-01") == "2021-01-01"
assert date_convert("2021-01-03") == "2021-01-01"
assert date_convert("2021-12-27") == "2021-12-27"
assert date_convert("2021-12-31") == "2021-12-27"
assert date_convert("2022-01-01") == "2022-01-01"
assert date_convert("2022-01-02") == "2022-01-01"
assert date_convert("2022-01-03") == "2022-01-03"

# 日期减法 - 减一天
dt = datetime.datetime.now() + datetime.timedelta(days=-1)
print(datetime.datetime.strftime(dt, "%Y-%m-%d %H:%M:%S"))

# 字符串转换为日期
t1 = datetime.datetime.strptime("2022-01-26 18:30:37", "%Y-%m-%d %H:%M:%S")
t2 = datetime.datetime.strptime("2022-01-26 00:00:00", "%Y-%m-%d %H:%M:%S")
print(t1)
print(t2)
