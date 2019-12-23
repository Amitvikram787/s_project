import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category, Product, Offer } from '../product.model';
import { ProductService } from 'src/app/services/product.service';
import { OfferService } from 'src/app/services/offer.service';
import { AuthService } from 'src/app/services/auth.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  category: Category;
  displayString: string;
  productList: Product[];
  filteredProducts: Product[];
  offerList: Offer[];
  filteredOffers: Offer[];
  filterBy: FormControl = new FormControl('');
  filterValue: FormControl = new FormControl('');
  sortBy: FormControl = new FormControl('offerId');
  sortOrder: FormControl = new FormControl('asc');
  constructor(private router: Router, private productService: ProductService, private offerService: OfferService, private authService: AuthService) {
    if (this.router.getCurrentNavigation().extras.state) {
      this.category = this.router.getCurrentNavigation().extras.state.category;
    } else {
      this.router.navigate(['/']);
    }
    if (!this.category) {
      this.displayString = this.router.getCurrentNavigation().extras.state.display;
      if (this.displayString) {
        if (this.displayString === 'Recommendations') {
          this.productService.getSuggestions(this.authService.loggedInUser.value.userId).subscribe(products => {
            this.productList = products;
            this.filteredProducts = this.productList;
            this.initializeSort();
          });
        } else if (this.displayString === 'Deals of the Day') {
          this.offerService.getAllOffers().subscribe(offers => {
            this.offerList = offers;
            this.filteredOffers = this.offerList;
            this.initializeSort();
          });
        } else if (this.displayString === 'All Products') {
          this.productService.getAllProductsInStock().subscribe(products => {
            this.productList = products;
            this.filteredProducts = this.productList;
            this.initializeSort();
          });
        }
      }
    } else {
      this.displayString = this.category.categoryName;
      this.productService.getAllProductsByCategory(this.category.categoryId).subscribe(products => {
        this.productList = products;
        this.filteredProducts = this.productList;
        this.initializeSort();
      });
    }
  }

  ngOnInit() {


  }
  initializeSort() {
    this.sortOrder.valueChanges.subscribe((value: string) => {
      this.sort(value);
    });
    this.sortBy.valueChanges.subscribe(() => {
      this.sort(this.sortOrder.value);
    });
    this.filterBy.valueChanges.subscribe(() => {
      this.filteredOffers = this.offerList;
      this.filterValue.setValue(null);
    });
    this.filterValue.valueChanges.subscribe((value: string) => {
      if (value) {
        if (this.offerList) {
          if (this.filterBy.value === 'productCode') {
            this.filteredOffers = this.offerList.filter(offer => offer.productCode.toLowerCase().includes(value.toLowerCase()));
          } else if (this.filterBy.value === 'productName') {
            this.filteredOffers = this.offerList.filter(offer => offer.productName.toLowerCase().includes(value.toLowerCase()));
          } else if (this.filterBy.value === 'discountRate') {
            this.filteredOffers = this.offerList.filter(offer => offer.discountRate === +value);
          } else if (this.filterBy.value === 'offerName') {
            this.filteredOffers = this.offerList.filter(offer => offer.offerName.toLowerCase().includes(value.toLowerCase()))
          }
        } else if (this.productList) {
          console.log('here');
          if (this.filterBy.value === 'productCode') {
            this.filteredProducts = this.productList.filter(product => product.productCode.toLowerCase().includes(value.toLowerCase()));
          } else if (this.filterBy.value === 'productName') {
            this.filteredProducts = this.productList.filter(product => product.productName.toLowerCase().includes(value.toLowerCase()));
          } else if (this.filterBy.value === 'brand') {
            this.filteredProducts = this.productList.filter(product => product.brand.toLowerCase().includes(value.toLowerCase()));
          }
        }
      } else {
        if (this.offerList) {
          this.filteredOffers = this.offerList;
        }
        if (this.productList) {
          this.filteredProducts = this.productList;
        }
      }
    });
  }
  sort(order: string) {
    if (this.offerList) {
      this.filteredOffers = this.filteredOffers.sort(this.offerComparator);
    } else {
      this.filteredProducts = this.filteredProducts.sort(this.productComparator)
    }
    if (order === 'dsc') {
      if (this.offerList) {
        this.filteredOffers = this.filteredOffers.reverse();
      } else {
        this.filteredProducts = this.filteredProducts.reverse();
      }
    }
  }
  compare(a, b) {
    return a < b ? -1 : a > b ? 1 : 0;
  }
  offerComparator = (a: Offer, b: Offer) => {
    const param = this.sortBy.value;
    if (param === 'product') {
      const currentProductName = a.productName;
      const nextProductName = b.productName;
      return this.compare(currentProductName, nextProductName);
    } else {
      return this.compare(a[param], b[param]);
    }
  }
  productComparator = (a: Product, b: Product) => {
    const param = this.sortBy.value;
    return this.compare(a[param], b[param]);
  }
  checkDate() {
    return this.filterBy.value !== 'offerDate';
  }

}
