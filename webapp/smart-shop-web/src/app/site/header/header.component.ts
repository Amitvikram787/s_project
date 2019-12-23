import { Component, OnInit, ViewChild } from '@angular/core';
import { User, UserSearchText } from '../user';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { dashboardUrl } from '../user-navigation-handler';
import { FormControl } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/product/product.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  loggedInUser: User = null;
  search: FormControl = new FormControl('');
  searchHistory: UserSearchText;
  filteredSearchHistory: UserSearchText;
  focused= true;
  productList: Product[];
  searchFocused = false;
  constructor(private router: Router, private authService: AuthService,private productService: ProductService) { }

  ngOnInit() {
    this.authService.loggedInUser.subscribe(user => this.loggedInUser = user);
    this.search.valueChanges.subscribe(value => {
      if(value) {
        this.searchFocused = true;
        this.focused = true;
        this.productList = this.productService.allProducts.filter(product => product.productName.toLowerCase().includes(value));
      }
    })
  }

  logout() {
    this.authService.logout();
  }

  home() {
    if(this.authService.loggedInUser.value) {
      const redirectUrl = dashboardUrl(this.authService.loggedInUser.value);
      this.router.navigate(redirectUrl);
    }
    else {
      this.router.navigate(["/"]);
    }
  }
  getSearchHistory() {
    if(this.authService.loggedInUser.value) {
      this.productService.getSearchHistory(this.authService.loggedInUser.value.userId).subscribe(searchHistory => {
        this.searchFocused = true;
        this.focused = true;
        this.searchHistory = searchHistory;
        this.filteredSearchHistory = this.searchHistory;
      });
    }
  }
  toggleFocus() {
    console.log(this.searchFocused);
    
    if(!this.searchFocused) {
      this.focused = false;
    }
  }
  blur() {
    this.searchFocused = false;
  }
  viewProduct(product: Product) {
    this.searchFocused = false;
    this.toggleFocus();
    this.router.navigate(['/product-list/details', product.productCode])
  }
  searchProduct(query: string) {
    this.searchFocused = false;
    this.toggleFocus();
    if(this.loggedInUser) {
      this.productService.addSearchHistory({
        username: this.loggedInUser.userId,
        searchText: query
      }).subscribe();
    }
    this.router.navigate(['/search/',query]);
  }
}
