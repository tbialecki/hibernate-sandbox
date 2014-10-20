package com.training.jpa

import com.training.jpa.config.DatabaseConfig
import com.training.jpa.model.Client
import com.training.jpa.model.Product
import com.training.jpa.model.Tag
import com.training.jpa.service.ClientService
import com.training.jpa.service.TagService
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Stepwise

import javax.inject.Inject
import javax.transaction.Transactional

import static com.training.jpa.OrderBuilder.anOrder

@ContextConfiguration(classes = DatabaseConfig)
@Stepwise
class ClientServiceSpec extends Specification {

    @Inject
    ClientService sut

    @Inject
    TagService ts;

    @Transactional
    def "should save product into database"() {
        given:
        def client = new Client()

        client.addOrder(anOrder()
                .withProduct(new Product("vodka", new Tag("strong-drinks"), 2))
                .withProduct(new Product("whisky", new Tag("strong-drinks"), 6))
                .build())

        client.addOrder(anOrder()
                .withProduct(new Product("rum", new Tag("strong-drinks"), 4))
                .withProduct(new Product("burbon", new Tag("strong-drinks"), 3))
                .build())

        client.addOrder(anOrder()
                .withProduct(new Product("beer", new Tag("light-drinks"), 1))
                .build())

        sut.saveClient(client)

        when:
        def clientFromDb = sut.findClient(client.id)

        then:
        clientFromDb.orders.products.price.flatten().sum() == 16
    }

    @Transactional
    def "should find client"() {
        when:
        def clientFromDb = sut.findClient(1)

        then:
        clientFromDb.orders.products.price.flatten().sum() == 16

    }
}
