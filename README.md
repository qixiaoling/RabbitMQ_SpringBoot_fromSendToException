Master branch has the normal procedure of sending a message from RabbitMQ; 
Exception branch handles wrong message e.g. minus salary which the message will try max 6 times and then go into a deadletterque and result finally in exception.
