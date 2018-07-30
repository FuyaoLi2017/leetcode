//count every number
public class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'R') x++;
            else if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }
}

//wrong answer why?
/*
*by Jitao Zhang
看半天我才看出来。那个Integer类型在map里声明的时候作为对象。
java里面好像是-128到127会做缓存也就是提前规定好统一的内存地址分配。
所以整数在这个范围内你可以用==判断相等。过了这个范围就不行了。你用equals就可以了
*https://blog.csdn.net/qq_38872310/article/details/79003571
*/
//wrong answer
class Solution{
	public boolean judgeCircle(String moves){
		char[] chars = moves.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < moves.length(); i++){
        		map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        if(map.get('R') == map.get('L') && map.get('U') == map.get('D'))
        	return true;
        return false;
	}
}

//revised version， also wrong
/*
*其实这样还是不行，因为equals是不能比较null的。所以还是会报错。
*https://blog.csdn.net/dafeige8/article/details/76719340
*/
class Solution{
	public boolean judgeCircle(String moves){
		char[] chars = moves.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < moves.length(); i++){
        		map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        if(map.get('R') == map.get('L') && map.get('U') == map.get('D'))
        	return true;
        return false;
	}
}

// 或者就是把null拿出来用"=="比较，其他的用equals
