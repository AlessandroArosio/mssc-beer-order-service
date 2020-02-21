package guru.sfg.beer.order.service.services;

import guru.sfg.beer.order.service.domain.BeerOrder;

/**
 * @author Alessandro Arosio - 21/02/2020 18:05
 */
public interface BeerOrderManager {
    BeerOrder newBeerOrder(BeerOrder beerOrder);
}
