package ar.com.plug.examen.rest;

import ar.com.plug.examen.app.rest.OrderController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderControllerIntegrationTest {

    @Autowired
    OrderController orderController;

    @Test
    void when1IsCreated_then1AreExpected() {

//        //Given
//        notificationController.deleteAll();
//
//        //When
//        //I create a Notification
//        NotificationDTO notification1 = new NotificationDTO();
//        notification1.setType("NEWS");
//        notification1.setMessage("test");
//        notification1.setUserId("1");
//        NotificationDTO notification2 = new NotificationDTO();
//        notification2.setType("STATUS");
//        notification2.setMessage("test");
//        notification2.setUserId("1");
//
//        notificationController.create(notification1);
//        notificationController.create(notification2);
//
//        //Then
//        Flux<Notification> result = notificationController.getAll();
//
//        Assertions.assertEquals(2, result.count().block());

    }
}
