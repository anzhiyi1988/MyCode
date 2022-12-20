#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
如何修改dataframe中的值
"""

import pandas as pd

df1 = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G3', 'G3', 'G3'],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'],
    'cl3': [1, 2, 3, 4, 5, 6, 7, 8, 9]
})
print('========================start')
print(df1)

print('用loc取值后，修改新结构，看看原结构是否改变')
df_new = df1.loc[:, ["cl1", "cl3"]]
print(df_new)

print('========================')
df_new['cl3'] = 10
print(df_new)

print('========================')
print(df1)
# df1 的值是不会发生变化的。
print('========================end')


df2 = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G4', 'G4', 'G4'],
    'cl2': ['A', None, 'C', 'D', 'E', None, None, 'K', 'L'],
    'cl3': [1, 2, 3, 4, 5, 6, 10, 11, 12]
})
print('========================start')
print(df2)

print('========================')
isNan = df2.cl2.isna()
print(isNan)


# print('========================1,原值改变，为啥')
# df2.loc[isNan, 'cl3'] = df2[isNan]['cl2']
# print(df2)

# print('========================2,原值改变,同1一样，为啥')
# df2.loc[~isNan, 'cl3'] = df2.loc[~isNan, 'cl2']
# print(df2)

print('========================3,原值不变')
df2[~isNan]['cl3'] = df2.loc[~isNan, 'cl2']
print(df2)

# print('========================4,原值不变')
# df2[~isNan]['cl3'] = df2[~isNan]['cl2']
# print(df2)


# 多列复制
# result[["statistical_month", "month_stock_amount"]] = result.apply(self.month_stock, axis=1).apply(pd.Series)