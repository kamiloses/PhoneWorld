
The main purpose of the application was to combine WebFlux with RabbitMQ and to check whether a Java agent can recognize connections between microservices through RabbitMQ as one trace. Initially, I used Micrometer with Zipkin, but unfortunately, Micrometer did not recognize RabbitMQ connections and eventually
zipkin showed few seperate traces.


I did one request that is responsible for making an order.
![image](https://github.com/user-attachments/assets/dc93c20a-02b2-49d6-94c5-a7d816cfc7f3)


Here is the full request as a graph (I wanted to use Zipkin, but the dependency graph did not display). 
Once you send a request, OrderService sends a RabbitMQ request to UserService for credentials (mainly account balance).
After a successful request, OrderService sends a request to ProductService to check the product cost. After that,
ProductService communicates with InventoryService to check if there are enough products in the inventory.


![image](https://github.com/user-attachments/assets/e2278d93-01e3-400a-b1a4-df1fea571c48)
![image](https://github.com/user-attachments/assets/6f20a8dd-9b0d-42ce-b894-f4d5165b1425)
In this app, I wasn't focused on exception handling, validation, etc. I just wanted to use observability tools.




I also created my own Gauge metrics that supervise product availability in the inventory.


![image](https://github.com/user-attachments/assets/0b9118fd-e351-4910-ac14-1e2c65c8a20b)
