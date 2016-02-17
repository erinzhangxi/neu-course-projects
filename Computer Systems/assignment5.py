# Assignment 5

# Part 1

# sum3

def sum3(nums):
  
   return sum(nums)
   
# rotate_left3
def rotate_left3(nums):
  
   return nums[1:] + [nums[0]]
   
# max_end3
def max_end3(nums):
  
   temp = max(nums[0], nums[2])
   return [temp,temp,temp]
   
# make_ends
def make_ends(nums):
  
   return [nums[0], nums[-1]]
   
# has23
def has23(nums):
  
   return 2 in nums or 3 in nums
   
# count_events
def count_evens(nums):
  
   return len( [x for x in nums if x % 2 == 0] )
   
# sum13
def sum13(nums):
  if len(nums) == 0:
    return 0
 
  for i in range(0, len(nums)):
    if nums[i] == 13:
      nums[i] = 0
      if i+1 < len(nums): 
        nums[i+1] = 0
  return sum(nums)
  
# big_diff
def big_diff(nums):
  
   return max(nums) - min(nums)
   
# sum67
def sum67(nums):
    while 6 in nums:
        rest = nums[nums.index(6):]
        nums = nums[:nums.index(6)] + rest[rest.index(7)+1:]
    return sum(nums)
    
# centered_average
def centered_average(nums):
  
  sort = sorted(nums)
  trimmed = sort[1:-1]
  
  return sum(trimmed) / (len(nums) - 2)
  
# has22
def has22(nums):
  i = 0 
  
  
  while i < len(nums) - 1:
    if nums[i] == nums[i + 1] == 2:
      return True
    else: 
      i = i + 1
  
  return False
      
# extra_end 
def extra_end(str):
  
  return 3*str[-2:]
  
# without_end 
def without_end(str):
  
  return str[1:-1]
  
# double_char
def double_char(str):
  
   return ''.join(c + c for c in str)
   
#  count_code
def count_code(str):
   sum = 0
   
   for i in range(len(str) - 3):
     if str[i:i+2] == 'co' and str[i+3] == 'e':
        sum += 1
        
   return sum
       
# end_other
def end_other(a, b):
  
   lower_a = a.lower()
   lower_b = b.lower()
   
   return lower_a.endswith(lower_b) or lower_b.endswith(lower_a)
   
# cat_dog
def cat_dog(str):
 count_cat = 0
  count_dog = 0
  for i in range(len(str)-2):
    if str[i:i+3] == 'dog':
      count_dog += 1
    if str[i:i+3] == 'cat':
      count_cat += 1
   
  return count_cat == count_dog
  
# count_hi
def count_hi(str):
  count = 0
  for i in range(len(str)-1):
    if str[i:i+2] == 'hi':
      count += 1
  return count
  
# xyz_there
def xyz_there(str):

   
   return str.count('xyz') - str.count('.xyz') > 0
   
  
# Part 2

# Problem 1
# Print the lines, words, chars of the given string

def wordCount(str):
    
    print str.count('\n'), len(str.split()), len(list(str))

string2 = "1234567890 \n a.b.c."
wordCount(string2)

  
# Problem 2

def mycount(str):

    mycount = [ str.count(c) for c in "0123456789" ]
    whitespace = sum(str.count(c) for c in " \t\n")
    others = len(str) - sum(mycount) - whitespace 
    mycount.append(whitespace)
    mycount.append(others)
  
    print mycount
    
string1 = "This is an example. 11009954"
string2 = "1234567890 \n a.b.c."
string3 = "1234567800 \t\n."
mycount(string3)
