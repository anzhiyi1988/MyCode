#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
列表拼接操作
"""
# extend 末尾一次性追加另一个序列中的多个值，扩展原列表
aList = ['123', 'xyz', 'zara', 'abc', '123']
bList = ['aaa', 'bbb']
aList.extend(bList)
print(aList)
print(bList)

aList.sort()
print(aList)

aList = set(aList)
print(aList)
aList = list(aList)

print(aList)

# 如果子列表为空对象，会报错
# TypeError: 'NoneType' object is not iterable
# aList = [123, 'xyz', 'zara', 'abc', 123]
# bList = None
# aList.extend(bList)
# print(aList)
# print(bList)