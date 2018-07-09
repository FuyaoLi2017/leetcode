int lengthOfLongestSubstring(char* s)
{
	int len=0;
    char *end=s,*temp;
	char* addressTable[128]={NULL};
	while(*end){
		temp = addressTable[*end];
		addressTable[*end]=end;
		if(temp>=s){
		len=end-s>len?end-s:len;
		s = temp+1;
		}end++;
	}
	len=end-s>len?end-s:len;
	return len;
}
