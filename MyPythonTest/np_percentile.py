import numpy as np
arr = [
8008,
8009,
8010,
8011,
8012,
8013,
8014,
8015,
8016,
8017
]

p = np.percentile(arr,90,method='inverted_cdf')
print(p)
p = np.percentile(arr,90,method='averaged_inverted_cdf')
print(p)
p = np.percentile(arr,90,method='closest_observation')
print(p)
p = np.percentile(arr,90,method='interpolated_inverted_cdf')
print(p)
p = np.percentile(arr,90,method='hazen')
print(p)
p = np.percentile(arr,90,method='weibull')
print(p)
p = np.percentile(arr,90)
print(p)
p = np.percentile(arr,90,method='median_unbiased')
print(p)
p = np.percentile(arr,90,method='normal_unbiased')
print(p)





# method : str, optional
# This parameter specifies the method to use for estimating the
#     percentile.  There are many different methods, some unique to NumPy.
# See the notes for explanation.  The options sorted by their R type
# as summarized in the H&F paper [1]_ are:
#
# 1. 'inverted_cdf'
# 2. 'averaged_inverted_cdf'
# 3. 'closest_observation'
# 4. 'interpolated_inverted_cdf'
# 5. 'hazen'
# 6. 'weibull'
# 7. 'linear'  (default)
# 8. 'median_unbiased'
# 9. 'normal_unbiased'