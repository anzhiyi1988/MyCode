#1.1才支持这么写的
import pandas as pd

data = [
    {'col1': 1, 'col2': 1, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': 'c2', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': 'c3', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c2', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c3', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c4', 'col4': 't1', 'col5': 't2'}
]
df1 = pd.DataFrame(data)

print(df1)

df2 = df1.pivot(index=['col1', 'col2'], columns='col3', values=['col4', 'col5'])
print(df2)

print(pd.__version__)
