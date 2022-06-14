import { Product } from './../common/product';
import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {



  private baseUrl = "http://localhost:8090/DealMarketUser/api/products"
  constructor(private httpClient : HttpClient) { }


  getProductList(theCategoryId : number) : Observable<Product[]> {

    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`

    return this.httpClient.get<GetResponse>(searchUrl).pipe(
      map(response => response._embedded.products)
    )
  }

  getProductCategories() {
    throw new Error('Method not implemented.');
  }



}



interface GetResponse {
  _embedded: {
    products: Product[];
  }

}
