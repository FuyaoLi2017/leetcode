/*
Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ‘a’ - ‘z’

There are no adjacent repeated characters with length > 9

Examples

“a1c0b2c4” → “abbcccc”
*/

// Straight Forward StringBuilder method
public class Solution {
  public String decompress(String input) {
    char[] array = input.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; i += 2) {
      int number = array[i + 1] - '0';
      for (int j = 0; j < number; j++) {
        sb.append(array[i]);
      }
    }
    return sb.toString();
  }
}

//
public class Solution {
  public String decompress(String input) {
    if (input.isEmpty()) {
      return input;
    }
    char[] array = input.toCharArray();
    return decodeLong(array, decodeShort(array));
  }

  private int decodeShort(char[] input) {
    int end = 0;
    for (int i = 0; i < input.length; i += 2) {
      int digit = getDigit(input[i + 1]);
      if (digit >= 0 && digit <= 2) {
        for (int j = 0; j < digit; j++) {
          input[end++] = input[i];
        }
      } else {
        // don't handle case when the number is larger than 2
        input[end++] = input[i];
        input[end++] = input[i + 1];
      }
    }
    return end;
  }

  // process
  private String decodeLong(char[] input, int length) {
    int newLength = length;
    for (int i = 0; i < length; i++) {
      int digit = getDigit(input[i]);
      if (digit > 2 && digit <= 9) {
        newLength += digit - 2;
      }
    }

    // fill the array in the backward direction
    char[] result = new char[newLength];
    int end = newLength - 1;
    for (int i = length - 1; i >= 0;i--) {
      int digit = getDigit(input[i]);
      if (digit > 2 && digit <= 9) {
        i--;
        for (int j = 0; j < digit; j++) {
          result[end--] = input[i];
        }
      } else {
        result[end--] = input[i];
      }
    }
    return new String(result);
  }

  private int getDigit(char digit) {
    return digit - '0';
  }
}
