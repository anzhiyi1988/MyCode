#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通过已有的python数据创建dataframe
"""

import pandas as pd

data = [
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'},
    {'col1': 'a', 'col2': 'b'}
]

cols = ['col1', 'col2', 'col3']
df1 = pd.DataFrame(data, columns=cols)
print('========================start')
print(df1)
