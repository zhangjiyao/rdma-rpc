package aio;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

public class Server {
    public static void main(String[] args) {
        try {
            TNonblockingServerSocket tNonblockingServerSocket=new TNonblockingServerSocket(7788);
            TProcessor processor =new HelloServer.AsyncProcessor<HelloServer.AsyncIface>(new HelloServerImp());
            TNonblockingServer.Args tnbargs=new TNonblockingServer.Args(tNonblockingServerSocket);
            tnbargs.processor(processor);
            tnbargs.transportFactory(new TFramedTransport.Factory());
            tnbargs.protocolFactory(new TCompactProtocol.Factory());
            TServer server=new TNonblockingServer(tnbargs);
            System.out.println("server starting....");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
