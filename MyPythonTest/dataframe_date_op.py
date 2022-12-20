#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
两个时间列相减
"""

import pandas as pd

data = [
    {'col1': '2022-09-22 00:00:03', 'col2': '2022-09-22 00:02:00'},
    {'col1': '2022-09-22 00:03:03', 'col2': '2022-09-22 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': '2022-09-22 00:01:00'},
    {'col1': '2022-09-22 00:01:03', 'col2': '2022-09-22 00:01:00'},
    {'col1': '', 'col2': '2022-09-22 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': ''},
    {'col1': '', 'col2': ''},
    {'col1': None, 'col2': '2022-09-22 00:01:00'},
    {'col1': '2022-09-22 00:00:03', 'col2': None},
]
cols = ['col1', 'col2']
df1 = pd.DataFrame(data, columns=cols)
print('原始数据')
print(df1)

df1[['col1', 'col2']] = df1[['col1', 'col2']].applymap(
    lambda x: pd.to_datetime(x, infer_datetime_format=True, errors='coerce')
)
print('转换为日期')
print(df1)

df1['duration'] = df1.col1 - df1.col2
print('求差值')
print(df1)
df1['duration_s1'] =  abs(df1.col1 - df1.col2).dt.seconds
df1['duration_s'] =  df1.col1.notna() & df1.col2.notna() & (abs(df1.col1 - df1.col2).dt.seconds > 60)
# df1['duration_s'] =  abs(df1.col1 - df1.col2).dt.total_seconds() > 60
print('求差秒')
print(df1)
