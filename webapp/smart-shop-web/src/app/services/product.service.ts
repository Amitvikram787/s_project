import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, BehaviorSubject } from 'rxjs';
import { Product, Category } from '../product/product.model';
import { UserSearchText, SearchText } from '../site/user';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  _baseUrl = `${environment.baseUrl}/product-service`;
  private _allProducts: BehaviorSubject<Product[]> = new BehaviorSubject(null);
  constructor(private http: HttpClient) { }
  set allProducts(products: Product[]) {
    this._allProducts.next(products);
  }
  get allProducts() {
    return this._allProducts.value;
  }
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this._baseUrl}/products`);
  }
  getAllProductsInStock(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this._baseUrl}/products/in-stock`);
  }
  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this._baseUrl}/categories`);
  }
  getAllProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this._baseUrl}/products/category/${categoryId}`);
  }
  getProductById(productCode: string): Observable<Product> {
    return this.http.get<Product>(`${this._baseUrl}/products/${productCode}`);
  }

  getSuggestions(userId:string) {
    console.log('suggestion');
    
    return this.http.get<Product[]>(`${this._baseUrl}/products/suggestion/${userId}`);
  }

  addProduct(product:Product)
  {
    return this.http.post<void>(`${this._baseUrl}/products`,product);
  }
  updateProduct(product: Product):Observable<any> {
    return this.http.put(`${this._baseUrl}/products`,product);
  }
  getCategoryById(categoryId: number): Observable<Category> {
    return this.http.get<Category>(`${this._baseUrl}/categories/${categoryId}`);
  }
  deleteCategoryById(categoryId: number): Observable<any> {
    return this.http.delete<void>(`${this._baseUrl}/categories/${categoryId}`);
  }
  addCategory(category: Category): Observable<any> {
    return this.http.post<void>(`${this._baseUrl}/categories`, category);
  }
  updateCategory(category: Category): Observable<any> {
    return this.http.put<void>(`${this._baseUrl}/categories`, category);
  }
  deleteProduct(productCode: string) {
    return this.http.delete(`${this._baseUrl}/products/${productCode}`);
  }
  getSearchHistory(userId: string): Observable<UserSearchText> {
    return this.http.get<UserSearchText>(`${this._baseUrl}/search-history/${userId}`);
  }
  addSearchHistory(searchText: SearchText): Observable<SearchText> {
    return this.http.post<SearchText>(`${this._baseUrl}/search-history`, searchText);
  } 
  deleteSearchHistory(userId: string): Observable<void> {
    return this.http.get<void>(`${this._baseUrl}/${userId}`);
  }
  searchProducts(query: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this._baseUrl}/products/search/${query}`);
  }
}
