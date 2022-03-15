#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import pandas as pd

df = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G3', 'G3', 'G3'],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'],
    'cl3': [2, 5, 7, 1, 2, 0, 0, 0, 0]
})
print(df)


# 对某一列进行去重，并形成一个新的df
df7 = pd.DataFrame({
    'cl1': df.cl1.unique()
})
print(df7)

print("-----------------------")
df1 = df.groupby('cl1', as_index=False)["cl3"].sum()
print(df1)

print("-----------------------")
df2 = df.groupby('cl1', as_index=False).sum()
print(df2)


print("-----------------------")
df3 = df.groupby('cl1', as_index=False).transform("sum")
print(df3)

print("-----------------------")
df4 = df.set_index(["cl1", "cl2"])
df5 = df4.groupby('cl1').sum()
print(df5)
df4['%'] = (df4/df5)
print(df4)

print("-----------------------")
df6 = df.groupby("cl1", as_index=False)['cl3'].transform("sum")
# df['cl4'] = df[['cl3']]/df6
df['cl4'] = df6["cl3"]
print(df6)
print(df)


df['cl5'] = df.apply(lambda x: x['cl3']+1 if x['cl4'] <= 0 else x['cl3'], axis=1)
print(df)
