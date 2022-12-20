#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通过已有的python数据创建dataframe
"""
import json
import pandas as pd
import numpy as np

data = [
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'},
    {'col1': 'a', 'col2': 'b', 'col3': 'c'}
]

cols = ['col1', 'col2', 'col3']
df1 = pd.DataFrame(data, columns=cols)
print('========================start')
print(df1)

# df1['json1'] = df1[['col2', 'col3']].apply(lambda x: x.to_json(), axis=1)
# print('========================1')
# print(df1)

# df2 = df1.json1.apply(json.loads).apply(pd.Series)
# print('========================2')
# print(df2)

# df3 = pd.concat([df1, df2], axis=1)
# print('========================3')
# print(df3)
#
# data1 = [
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}},
#     {'col1': 'a', 'col2': 'b', 'col3': {"col4": "d", "col5": "e"}}
# ]
#
# cols = ['col1', 'col2', 'col3']
# df4 = pd.DataFrame(data1, columns=cols)
# print('========================4')
# print(df4)
#
# print('========================5')
# print(df4.col3)
#
# print('========================6')
# print(df4.col3.apply(pd.Series))


