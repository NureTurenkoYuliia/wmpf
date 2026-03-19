# Level 1
# 4. Напишіть функцію, яка приймає три параметри (a, b, c) і виводить на екран найменше з них.
def min_value(a, b, c):
    if a <= b and a <= c:
        print(a)
    elif b <= a and b <= c:
        print(b)
    else:
        print(c)

# Level 2
# 4. Напишіть функцію, яка приймає рядок та повертає його обернений варіант. Наприклад, "hello" повинно повернути "olleh".
def reverse(input):
    reversed = ""
    for char in input:
        reversed = char + reversed
    return reversed

def reverse_2(input):
    return input[::-1]

# Level 3
# 4. Реалізуйте програму, яка визначає, чи є слово паліндромом (читається однаково з обох боків).
def is_palindrome(input):
    left, right = 0, len(input) - 1
    input = input.lower()
    while left < right:
        if input[left] != input[right]:
            return False
        left += 1
        right -= 1
    return True

def is_palindrome_2(input):
    input = input.lower()
    return input == input[::-1]

# Level 4
# 4. Розробіть алгоритм сортування масиву чисел методом швидкого сортування (QuickSort) та виведіть відсортований масив.
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)


# Виклик функцій
min_value(3, 7, 2)

print(reverse("nure"))
print(reverse_2("nure"))

print(is_palindrome("Level"))
print(is_palindrome_2("Level"))

numbers = [5, 7, 3, 8, 4, 2, 7, 1, 10]
sorted_numbers = quick_sort(numbers)
print(sorted_numbers)