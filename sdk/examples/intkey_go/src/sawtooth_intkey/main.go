package main

import (
	"os"
	intkey "sawtooth_intkey/handler"
	"sawtooth_sdk/logging"
	"sawtooth_sdk/processor"
)

func main() {
	endpoint := "tcp://localhost:40000"
	if len(os.Args) > 0 {
		endpoint = os.Args[1]
	}

	logger := logging.Get()
	logger.SetLevel(logging.INFO)

	prefix := intkey.Hexdigest("intkey")[:6]
	handler := intkey.NewIntkeyHandler(prefix)
	processor := processor.NewTransactionProcessor(endpoint)
	processor.AddHandler(handler)
	processor.Start()
}
