#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通过已有的python数据创建dataframe
"""

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

"""
有两种方式
一个是format
一个是format_map
"""

msg = '【高级风险】预测单{col1}期望交付时间为：{col2},截止{col2}还未及时锁量，请处理！'

# msg = msg.format(
#     forecast_id=1,
#     expect_time='2022',
#     warn_time='2022'
# )

df1['msg'] = df1.apply(
    lambda x: msg.format_map(x.to_dict()),
    axis=1
)
print(df1)

df1['counts'] = 1
df2 = df1.groupby(
    ['col1']
).agg({
    'col2': max,
    'counts': np.size
})
print(df2)
