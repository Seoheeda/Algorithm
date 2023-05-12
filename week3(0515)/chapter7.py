### < 두 수의 합 > ###

# nums = [2, 7, 11, 15]
# target = 9

# def twoSum(nums, target):
#     for i in range(len(nums)):
#         for j in range(len(nums)):
#             if nums[i] + nums[j] == target:
#                 return [i, j]

# print(twoSum(nums, target))


# def twoSum(nums, target):
#     for i, n in enumerate(nums):
#         complement = target - n

#         if complement in nums[i + 1:]:
#             return [nums.index(n), nums[i + 1:].index(complement) + (i + 1)]

# print(twoSum(nums, target))


# def twoSum(nums, target):
#     nums_map = {}
#     for i, num in enumerate(nums):
#         nums_map[num] = i
    
#     for i, num in enumerate(nums):
#         if target - num in nums_map and i != nums_map[target - num]:
#             return [i, nums_map[target - num]]

# print(twoSum(nums, target))



### < 빗물 트래핑 > ###

height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]

# def trap(height):
#     if not height:
#         return 0
    
#     volume = 0
#     left, right = 0, len(height) - 1
#     left_max, right_max = height[left], height[right]

#     while left < right:
#         left_max, right_max = max(height[left], left_max), max(height[right], right_max)

#         if left_max <= right_max:
#             volume += left_max - height[left]
#             left += 1

#         else:
#             volume += right_max - height[right]
#             right -= 1
#     return volume

# print(trap(height))


# def trap(height):
#     stack = []
#     volume = 0

#     for i in range(len(height)):
#         while stack and height[i] > height[stack[-1]]:
#             top = stack.pop()

#             if not len(stack):
#                 break
        
#             distance = i - stack[-1] - 1
#             waters = min(height[i], height[stack[-1]]) - height[top]

#             volume += distance * waters

#         stack.append(i)
#     return volume

# print(trap(height))



### < 세 수의 합 > ###

nums = [-1, 0, 1, 2, -1, -4]

# def threeSum(nums):
#     results = []
#     nums.sort()

#     for i in range(len(nums) - 2):
#         if i > 0 and nums[i] == nums[i - 1]:
#             continue
#         for j in range(i + 1, len(nums) - 1):
#             if j > i + 1 and nums[j] == nums[j - 1]:
#                 continue
#             for k in range(j + 1, len(nums)):
#                 if k > j + 1 and nums[k] == nums[k - 1]:
#                     continue
#                 if nums[i] + nums[j] + nums[k] == 0:
#                     results.append([nums[i], nums[j], nums[k]])
#     return results

# print(threeSum(nums))


# def threeSum(nums):
#     results = []
#     nums.sort()

#     for i in range(len(nums) - 2):
#         if i > 0 and nums[i] == nums[i - 1]:
#             continue
#         left, right = i + 1, len(nums) - 1
#         while left < right:
#             sum = nums[i] + nums[left] + nums[right]
#             if sum < 0:
#                 left += 1
#             elif sum > 0:
#                 right -= 1
#             else:
#                 results.append([nums[i], nums[left], nums[right]])

#                 while left < right and nums[left] == nums[left + 1]:
#                     left += 1
#                 while left < right and nums[right] == nums[right - 1]:
#                     right -= 1
#                 left += 1
#                 right -= 1

#     return results

# print(threeSum(nums))



### < 배열 파티션 1 > ###

# nums = [1, 4, 3, 2]

# def arrayPairSum(nums):
#     sum = 0
#     pair = []
#     nums.sort()

#     for n in nums:
#         pair.append(n)
#         if len(pair) == 2:
#             sum += min(pair)
#             pair = []
#     return sum

# print(arrayPairSum(nums))


# def arrayPairSum(nums):
#     sum = 0
#     nums.sort()

#     for i, n in enumerate(nums):
#         if i % 2 == 0:
#             sum += n
    
#     return sum

# print(arrayPairSum(nums))


# def arrayPairSum(nums):
#     return sum(sorted(nums)[::2])

# print(arrayPairSum(nums))



### < 자신을 제외한 배열의 곱 > ###

# nums = [1, 2, 3, 4]


# def productExceptSelf(nums):
#     out = []
#     p = 1

#     for i in range(len(nums)):
#         out.append(p)
#         p = p * nums[i]

#     p = 1

#     for i in range(len(nums) - 1, - 1, -1):
#         out[i] = out[i] * p
#         p = p * nums[i]
    
#     return out

# print(productExceptSelf(nums))


### < 주식을 사고팔기 가장 좋은 시점 > ###

prices = [7, 1, 5, 3, 6, 4]

# def maxProfit(prices):
#     max_price = 0

#     for i, price in enumerate(prices):
#         for j in range(i, len(prices)):
#             max_price = max(prices[j] - price, max_price)

#     return max_price

# print(maxProfit(prices))

import sys

def maxProfit(prices):
    profit = 0
    min_price = sys.maxsize

    for price in prices:
        min_price = min(min_price, price)
        profit = max(profit, price - min_price)

    return profit

print(maxProfit(prices))
