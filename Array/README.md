### Speed of operating array
copy an array is slow, copy certain elements are much quicker
> leetcode 048

### How to initialize a ArrayList
https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
`ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));`

### 对于2D array的题
- 注意可能利用bit操作，lc289 game of life.
- 一般都是先取出来横向有多少点，纵向有多少点，移动指针进行操作
