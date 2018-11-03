package aio;


import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        try {
            TAsyncClientManager clientManager=new TAsyncClientManager();
            TNonblockingTransport transport=new TNonblockingSocket("localhost",7788);
            TProtocolFactory tProtocolFactory=new TCompactProtocol.Factory();
            HelloServer.AsyncClient asyncClient=new HelloServer.AsyncClient(tProtocolFactory,clientManager,transport);
            try {
                asyncClient.sayString("zhang ", new AsyncMethodCallback<String>() {
                    public void onComplete(String s) {
                        System.out.println("ss"+s);
                    }

                    public void onError(Exception e) {
                        System.out.println("sss");
                    }
                });
            } catch (TException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
