package dev.practice.chapter15.filter;

import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public class BookRouterFunctionFilter implements HandlerFilterFunction {
    @Override
    public Mono filter(ServerRequest request, HandlerFunction next) {
        String path = request.requestPath().value();

        return next.handle(request).doAfterTerminate(() -> {
            System.out.println("path: " + path + ", status: " + request.exchange().getResponse().getStatusCode());
        });
    }
}
