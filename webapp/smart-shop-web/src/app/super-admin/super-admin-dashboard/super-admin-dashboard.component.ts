import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { User } from 'src/app/site/user';
import { UserService } from 'src/app/services/user.service';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Feedback } from 'src/app/site/feedback.model';

@Component({
  selector: 'app-super-admin-dashboard',
  templateUrl: './super-admin-dashboard.component.html',
  styleUrls: ['./super-admin-dashboard.component.css']
})
export class SuperAdminDashboardComponent implements OnInit {
  constructor( private uesrService:UserService, private feedbackService:FeedbackService) { }
   
  feedback:Feedback;
  ngOnInit() {
   
 }

  
 
  }


