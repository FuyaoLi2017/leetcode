/*
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?
*/
// a high vote answer
class Solution {
    public int numUniqueEmails(String[] emails) {
    Set<String> normalized = new HashSet<>(); // used to save simplified email address, cost O(n) sapce.
    for (String email : emails) {
        String[] parts = email.split("@"); // split into local and domain parts.
        // 正则表达式，"//" 转义为 "/", "/+" 一起总体代表匹配加号
        String[] local = parts[0].split("\\+"); // split local by '+'.
        normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.
    }
    return normalized.size();
    }
}


// my solution
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> domain = new HashSet<>();
        Set<String> local = new HashSet<>();
        // first we need to compare domain names
        // then we can compare the local name
        for (int i = 0; i < emails.length; i++) {
            String email = emails[i];
            String[] address = email.split("@");
            if (domain.contains(address[1])) {
                String processedEmail = processEmail(email);
                if (!local.contains(processedEmail)) {
                    local.add(processedEmail);
                }
            } else {
                local.add(processEmail(email));
                domain.add(address[1]);
            }
        }
        return local.size();

    }

    private String processEmail(String email) {
        String[] address = email.split("@");
        StringBuilder sb = new StringBuilder(address[0]);
        String local = null;
        for (int i = 0; i < sb.length();) {
            if (sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
            } else if (sb.charAt(i) == '+') {
                local = sb.substring(0, i);
                break;
            } else {
                i++;
            }
        }
        if (local != null) {
            return local + "@" + address[1];
        }
        return sb.toString() + "@" + address[1];
    }
}
