import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { UserFeedback } from 'src/app/site/feedback.model';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {
  totalProducts: number = 0;
  totalCategories: number = 0;
  userFeedback:UserFeedback[];
  constructor(private productService: ProductService ,private feedbackService:FeedbackService) { }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(products => this.totalProducts = products.length);
    this.productService.getAllCategories().subscribe(categories => this.totalCategories = categories.length)
    this.feedbackService.getAllFeedback().subscribe(feedbacks=>{this.userFeedback=feedbacks
      
        }) 
  }

}
