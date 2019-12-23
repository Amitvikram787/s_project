import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map, switchMap } from 'rxjs/operators';
import { ProductService } from 'src/app/services/product.service';
import { Product } from '../product.model';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  searchResults: Product[];
  filteredSearchResults: Product[];
  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService) {
    this.activatedRoute.params.pipe(
      map(params => params['query']),
      switchMap(query => this.productService.searchProducts(query))
    ).subscribe((products: Product[]) => {
      this.searchResults = products;
      this.filteredSearchResults = this.searchResults;
    });
   }

  ngOnInit() {
  }

}
