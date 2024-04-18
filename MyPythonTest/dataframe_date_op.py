#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
两个时间列相减
"""
import datetime
import pandas as pd

# 基础数据准备
data = [
    {'col1': '2022-09-22 00:00:03', 'col2': '2023-04-18 00:02:00'},
    {'col1': '2022-09-22 00:03:03', 'col2': '2023-04-10 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': '2023-04-10 00:01:00'},
    {'col1': '2022-09-22 00:01:03', 'col2': '2023-04-10 00:01:00'},
    {'col1': '',                    'col2': '2023-04-04 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': ''},
    {'col1': '',                    'col2': ''},
    {'col1': None,                  'col2': '2023-04-03 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': None},
]
cols = ['col1', 'col2']
df = pd.DataFrame(data, columns=cols)
print('原始数据')
print(df)
df[['col1', 'col2']] = df[['col1', 'col2']].apply(
    lambda x: pd.to_datetime(x, infer_datetime_format=True, errors='coerce')
)
print('转换为日期')
print(df)


# 测试1 求差值
# df['duration'] = (df.col1 - df.col2).dt.total_seconds()
# print('求差值')
# print(df)

# 测试2 和当前时间比，15天之内的留下
condition = (
        (~df['col2'].isna()) &
        (df['col2'] > (datetime.datetime.now() + datetime.timedelta(days=-15)))
)

df = df.loc[condition,:]
print('过滤15天外的')
print(df)
