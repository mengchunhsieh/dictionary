package dictionary;

/**
 * Dictionary Class
 */
public class Dictionary {

    Message message = new Message();

    TrieNode rootTridNode = new TrieNode(Boolean.FALSE);

    /**
     * Read through dictionary to find target word
     *
     * @param word target word to find
     * @return message
     */
    public String read(String word) {
        word = word.toLowerCase();

        // get the first char to process search
        if (rootTridNode.getChildren().containsKey(word.charAt(0))) {

            // remain string after found the first chat
            String remainString = word.substring(1);

            // recursively process the first chat and update the target string
            return find(remainString, rootTridNode.getChildren().get(word.charAt(0))) ?
                    message.wordFound + word
                    :message.wordNotFound + word;
        } else {
            // return message if the world is not found
            return message.wordNotFound + word;
        }
    }

    /**
     * Recursively process and serch the first chat of input string
     *
     * @param word word to search
     * @param trieNode current trie node to deal with
     * @return
     */
    private boolean find(String word, TrieNode trieNode) {

        // break point
        if (word.length() == 0) {
            return trieNode.getIsEndOfWord();
        }

        // search if there is next chat contain in the current trie node library
        return trieNode.getChildren().containsKey(word.charAt(0)) ?
                find(word.substring(1),trieNode.getChildren().get(word.charAt(0))) : Boolean.FALSE;
    }

    /**
     * Add new word to dictionary
     *
     * @param word The word to add
     */
    public String add(String word) {
        word = word.toLowerCase();

        // process first char to add
        if (!rootTridNode.getChildren().containsKey(word.charAt(0))) {
            rootTridNode.getChildren().put(word.charAt(0), new TrieNode(Boolean.FALSE));
        }

        // remain string after added the first chat
        String remainString = word.substring(1);

        // recursively add the first chat then remove it and update the input string
        addHelper(remainString,rootTridNode.getChildren().get(word.charAt(0)));
        return message.wordAdded + word;
    }

    /**
     * Recursively add char to trie tree
     *
     * @param word current word to add
     * @param trieNode current trie node to deal with
     */
    private void addHelper(String word, TrieNode trieNode) {

        TrieNode nextTridNode;

        // check the first chat if need to create a new trie node or get from exist library
        if (trieNode.getChildren().containsKey(word.charAt(0))) {
            nextTridNode = trieNode.getChildren().get(word.charAt(0));
        } else {
            nextTridNode = new TrieNode(Boolean.FALSE);
            trieNode.getChildren().put(word.charAt(0), nextTridNode);
        }

        // break point
        if (word.length() == 1) {
            nextTridNode.setEndOfWord(Boolean.TRUE);
            return;
        }

        // start recursively process the above step until break point
        addHelper(word.substring(1),nextTridNode);
    }

    /**
     * Delete word form dictionary
     *
     * @param word word to delete
     */
    public String delete(String word) {
        word = word.toLowerCase();

        // to mark the trie node that is the start point to delete the child node
        TrieNode deletePointer = null;
        char markedChar = '\0';

        // set up current root
        TrieNode currentRoot = rootTridNode;

        // check input string is ok to be process
        for (int i = 0; i < word.length(); i++) {

            // case if input string's length is over the trie high, not able to find the path
            if (currentRoot.getChildren().get(word.charAt(i)) == null) {
                return message.wordUpdateFailNotFound + word;
            }

            // update and move to process next if has next
            if (!currentRoot.getChildren().isEmpty()) {
                deletePointer = currentRoot;
                markedChar = word.charAt(i);
            }
            // update current root for next index of char
            currentRoot = currentRoot.getChildren().get(word.charAt(i));
        }


        // case if current string is a sub string of other wold
        if (currentRoot.getIsEndOfWord()) {

            // check if has next
            if (currentRoot.getChildren().isEmpty()) {

                // reach the end and remove mark chat
                deletePointer.getChildren().remove(markedChar);

            } else {
                // update flage since it's a substring of other, and make it as deleted
                currentRoot.setEndOfWord(Boolean.FALSE);
            }
        }
        return message.wordDelete + word;
    }

    /**
     * Update word to dictionary
     *
     * @param oldWord word to update
     * @param updateWord new word
     */
    public String update(String oldWord, String updateWord) {
        oldWord = oldWord.toLowerCase();
        // check if dictionary contains the word
        if (read(oldWord).equals(message.wordFound + oldWord)) {
            updateWord = updateWord.toLowerCase();

            // remove the previous word
            delete(oldWord);

            // add the new update
            add(updateWord);

            return message.wordUpdated + updateWord;
        }

        return message.wordUpdateFail + oldWord;
    }
}
