import pandas as pd
import numpy as np

data = [
    {'col1': 1, 'col2': 1, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': np.NaN, 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': np.NaN, 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': np.NaN, 'col3': 'c2', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': np.NaN, 'col3': 'c3', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c4', 'col4': 't1', 'col5': 't2'}
]
df = pd.DataFrame(data)
print(df)
print(df.dtypes)

# df[['col2','col3']] = df[['col2','col3']].astype(str)
# print(df)
# print(df.dtypes)

condition = df[['col2','col3']].isna()
print(condition)
df[['col2','col3']] = df[['col2','col3']].astype(str)
print(df)
print(df.dtypes)
df[['col2','col3']] = df[['col2','col3']].mask(condition,np.NaN)
print(df)
print(df.dtypes)


np.percentile