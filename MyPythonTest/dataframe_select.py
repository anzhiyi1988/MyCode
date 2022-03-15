# !/usr/bin/env python3
# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np

df = pd.DataFrame({
    'cl1': [np.nan, 'C', 'D', np.nan, np.nan, np.nan, np.nan, np.nan, np.nan],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'K', 'L'],
    'cl3': [1, 2, 3, 4, 5, 6, 10, 11, 12]
})
print('-------------------')
print(df)

print('-------------------下面两种方式是获取列数据为series类型，所以可以使用series类型内的方法，')
a = df.cl1
print('取一个系列：', a)
print('取系列中一个值：', a[0])

c = df["cl1"]
print('取一个系列：', c)

print('------------------- 下面两种方式都是获取切片，返回的是dataframe')
d = df[["cl1"]]
print(d)

e = df.loc[:, ["cl1"]]
print(e)

print('------------------- 先获取一行，然后在取其中的值')
raw = df.loc[df.cl2 == 'D']
print('一行：', raw)
a = raw.cl1
print('行中一个值', a)
print('-------------------下面这是会报错的，因为a这个series类型的index是会集成dataframe的，所以此处a中只有一条数据，但是他索引是3')
# print(a[0])

print('-------------------构造新的series类型测试一下，字符索引')
ser05 = pd.Series([1, 2, 3, 4, 5], index=['a', 'b', 'c', 'd', 'e'])
print(ser05)
print('------------------')
print(ser05[1])  # 为什么这个用1就可以呢？ 因为索引是字符，不是数字，所以这里可以用字符，比如下面，索引是数字，在用1，就不行了
print('------------------')
print(ser05['a'])
print('------------------')
print(ser05['d'])

print('-------------------构造新的series类型测试一下，数字索引')
ser05 = pd.Series([1, 2, 3, 4, 5], index=[6, 7, 8, 9, 10])
print(ser05)
print('------------------')
# print(ser05[1])  #这个1就不可以！！！
print('------------------')
print(ser05[6])
print('------------------')
print(ser05[8])

print('-------------------行遍历一个dataframe数据集')
for index, row in df.iterrows():
    print(index)
    print(row)
    print(df.iloc[index])
