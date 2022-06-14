import { Product } from './../common/product';
import { HttpClient } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {



  private baseUrl = "http://localhost:8090/DealMarketUser/api/products";
  private categoryUrl =  "http://localhost:8090/DealMarketUser/api/product-category";
  constructor(private httpClient : HttpClient) { }


  getProductList(theCategoryId : number) : Observable<Product[]> {

    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`

    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(
      map(response => response._embedded.products)
    )
  }


  getProductCategories(): Observable<ProductCategory[]> {

    return this.httpClient.get<GetResponseProductCategory>(this.categoryUrl).pipe(
      
      map(response => response._embedded.productCategory)
      
    );
  }



}



interface GetResponseProducts {
  _embedded: {
    products: Product[];
  }

}

interface GetResponseProductCategory {
  _embedded: {
    productCategory: ProductCategory[];
  }
}
