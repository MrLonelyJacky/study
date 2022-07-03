package domain.learn4.repository;

import domain.learn4.AggregateRoot;
import domain.learn4.Identifier;

public interface Repository <T extends AggregateRoot<ID>, ID extends Identifier>{
    void save(T t);
}
