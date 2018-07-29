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
