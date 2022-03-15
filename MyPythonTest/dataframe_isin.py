#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
选出df2中，不在df1中的数据
"""
import pandas as pd

df1 = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G3', 'G3', 'G3'],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'],
    'cl3': [1, 2, 3, 4, 5, 6, 7, 8, 9]
})
print('----------df1：')
print(df1)

df2 = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G4', 'G4', 'G4'],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'm', 'J', 'K', 'L'],
    'cl3': [1, 2, 3, 4, 5, 6, 10, 11, 12]
})
print('----------df2：')
print(df2)

filter2 = (
        ~(df2.cl1.isin(df1.cl1.tolist())) |
        ~(df2.cl2.isin(df1.cl2.tolist()))
)
df3 = df2.loc[filter2, ["cl1", "cl2", "cl3"]]
print('----------df3：')
print(df3)
