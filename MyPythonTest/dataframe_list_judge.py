import pandas as pd

data = [
    {'rack_id': 1, 'valid_ip': True},
    {'rack_id': 1, 'valid_ip': True},
    {'rack_id': 1, 'valid_ip': True},
    {'rack_id': 1, 'valid_ip': True},
    {'rack_id': 1, 'valid_ip': True},
    {'rack_id': 1, 'valid_ip': False},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 2, 'valid_ip': True},
    {'rack_id': 3, 'valid_ip': False},
    {'rack_id': 3, 'valid_ip': False},
    {'rack_id': 3, 'valid_ip': False},
    {'rack_id': 3, 'valid_ip': False},
    {'rack_id': 3, 'valid_ip': False},
    {'rack_id': 3, 'valid_ip': False},
]
df = pd.DataFrame(data)
print(df)

df = df.groupby(by=['rack_id'],as_index=False).agg({
    'valid_ip': lambda x: any(set(x))
})

print(df)
