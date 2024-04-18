"""
    日志需求:
        1。要求将所有级别的所有日志都写入磁盘文件中
        2。all.log文件中记录所有的日志信息,日志格式为：日期和时间 - 日志级别 - 日志信息
        3。error.log文件中单独记录error及以上级别的日志信息,日志格式为：日期和时间 - 日志级别 - 文件名[:行号] - 日志信息
        4。要求all.log在每天凌晨进行日志切割
"""
import logging
from logging import handlers
import datetime

# 创建日志记录器
logger = logging.getLogger('mylog')

# 设置日志最低级别为DEBUG,这个是全局的，其他的handler只能比这个低，比这个高也没用
logger.setLevel(logging.DEBUG)

# 创建handler  文件类型，并且可以自行分割
# filename:日志文件名称
# when:指定文件的切割时间间隔,可选值为S(秒),M(分钟),H(小时),D(天),W0-W6(周一至周日),midnight(每天凌晨)
# backupCount:指定保留的日志文件数量
# interval:指定切割时间间隔的数量
All_Handler = logging.handlers.TimedRotatingFileHandler(
    filename='all.log', when='midnight', interval=1, backupCount=7, atTime=datetime.time(0, 0, 0, 0)
)
All_Handler.setLevel(logging.DEBUG)
all_fmt = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
All_Handler.setFormatter(all_fmt)
logger.addHandler(All_Handler)

# 创建handler ，文件类型
error_handler = logging.FileHandler('error.log')
error_handler.setLevel(logging.ERROR)
err_fmt = logging.Formatter('%(asctime)s - %(levelname)s - %(filename)s[:%(lineno)d] - %(message)s')
error_handler.setFormatter(err_fmt)
logger.addHandler(error_handler)

# 发送日志消息
logger.debug('This is a debug message')
logger.info('This is an info message')
logger.warning('This is a warning message')
logger.error('This is an error message')
logger.critical('This is a critical message')
