"""
修饰器测试
"""


def decorator(func):
    print('decorator start')
    obj = func()
    print('decorator end')
    return obj


@decorator
def some_func():
    print('some_func start')
    print('some_func end')
    return 'hello some_func'


"""
某个方法增加了装饰器，调用这个方法的时候，需要看装饰器返回的是什么，
    如果返回的是方法，则需要加`()`调用
    如果放回的是数据，则不需要加`()`调用
"""
result = some_func
print(result)

'''
带参数的方法，如果加修饰类
'''


def decorator_with_args(func):
    def inner(*args, **kwargs):
        print('decorator_with_args start')
        obj = func(*args, **kwargs)
        print('decorator_with_args end')
        return obj

    return inner


@decorator_with_args
def some_func_with_args(msg):
    print('some_func_with_args start')
    print('some_func_with_args end')
    return 'hello {}'.format(msg)


'''
此处装饰器返回的肯定是方法，则需要加`()`调用
'''
result = some_func_with_args('some_func_with_args')
print(result)
