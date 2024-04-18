desc_template = '{{"demand_order_id":{data_id},"server_sn":"{server_sn}","duration":{duration}}}'

map = {
    'data_id': 1,
    'server_sn': 'a',
    'duration': 23
}

str = desc_template.format_map(map)

print(str)
