KEY="API_KEY"
PROMPT="Given the following code snippet, generate all possible assertions following the formal grammar rules. For each assertion, rank the list of all possible assertions. Code: shouldEqualGivenSelf() { OrderedSet<Object> orderedSet = new OrderedSet<Object>(); boolean equals = orderedSet.equals(orderedSet); <AssertPlaceHolder>; }"


curl 'https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.3/v1/chat/completions' \
--header "Authorization: Bearer $KEY" \
--header 'Content-Type: application/json' \
--data "{
    \"model\": \"mistralai/Mistral-7B-Instruct-v0.3\",
    \"messages\": [
        { \"role\": \"user\", \"content\": \"$PROMPT\" }
    ],
    \"temperature\": 0.5,
    \"max_tokens\": 2048,
    \"top_p\": 0.7
}" | jq .
