# Chatting-Program

This program is the Chatting Program.
But it's Not just simple Chatting program.
This program offers encryption.

# Structure

Server generates RSA public key and private key.
And Send public key to Client.

Client received RSA public key and generates AES 256 secret key.
Then, Client encrypt secret key with RSA public key and sends it to Server.
Server receives encrypted secret key and decrept it with RSA private key.

After that, they send and received message with AES 256 Encryption.

# Usage

Client User should enter the Server's IP address in the variable "host_ip"

After connected, they will exchange Keys and set the SECURE-environment.

