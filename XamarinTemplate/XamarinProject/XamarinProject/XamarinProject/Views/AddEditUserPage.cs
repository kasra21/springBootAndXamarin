﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using XamarinProject.Models;
using XamarinProject.ViewModels;

namespace XamarinProject.Views
{
	class AddEditUserPage : ContentPage
	{

		readonly User existingUser;
		readonly Entry usernameCell, emailCell, firstCell, lastCell;
		readonly IList<User> users;
		readonly Controller controller;

		//existingUser is also used as flag for update/add
		public AddEditUserPage(Controller controller, IList<User> users, User existingUser = null) {
			this.controller = controller;
			this.users = users;
			this.existingUser = existingUser;

			var tableView = new TableView {
				Intent = TableIntent.Form,
				Root = new TableRoot(existingUser != null ? "Existing User" : "New User") {
										new TableSection() {
												new TextCell { Text="User Detail", TextColor=Color.FromHex("#ffffff") },
												new ViewCell {
													View = new StackLayout {
														Orientation=StackOrientation.Horizontal,
														Children = {
															new Label{
																TextColor=Color.FromHex("#33FFC0"),
																Text="Username",
																VerticalTextAlignment=TextAlignment.Center},
															(usernameCell = new LineEntry{
																HorizontalOptions=LayoutOptions.FillAndExpand,
																TextColor=Color.FromHex("#ffffff"),
																Text = (existingUser != null) ? existingUser.username : null,
																IsEnabled = (existingUser == null)
															})
														},
													}
												},
												new ViewCell {
													View = new StackLayout {
														Orientation=StackOrientation.Horizontal,
														Children = {
															new Label{
																TextColor=Color.FromHex("#33FFC0"),
																Text="Email",
															  VerticalTextAlignment=TextAlignment.Center},
															(emailCell = new LineEntry{
																HorizontalOptions=LayoutOptions.FillAndExpand,
																TextColor=Color.FromHex("#ffffff"),
																Text = (existingUser != null) ? existingUser.email : null
															})
														},
													}
												},
												new ViewCell {
													View = new StackLayout {
														Orientation=StackOrientation.Horizontal,
														Children = {
															new Label{
																TextColor=Color.FromHex("#33FFC0"),
																Text="First Name",
																VerticalTextAlignment=TextAlignment.Center},
															(firstCell = new LineEntry{
																HorizontalOptions=LayoutOptions.FillAndExpand,
																TextColor=Color.FromHex("#ffffff"),
																Text = (existingUser != null) ? existingUser.first : null
															})
														},
													}
												},
												new ViewCell {
													View = new StackLayout {
														Orientation=StackOrientation.Horizontal,
														Children = {
															new Label{
																TextColor=Color.FromHex("#33FFC0"),
																Text="Last Name",
																VerticalTextAlignment=TextAlignment.Center},
															(lastCell = new LineEntry{
																HorizontalOptions=LayoutOptions.FillAndExpand,
																TextColor=Color.FromHex("#ffffff"),
																Text = (existingUser != null) ? existingUser.last : null
															})
														},
													}
												},
										},
								}
			};

			Button button = new Button() {
				BackgroundColor = existingUser != null ? Color.Gray : Color.Green,
				TextColor = Color.White,
				Text = existingUser != null ? "Update" : "Add User",
				BorderRadius = 0,
			};
			button.Clicked += OnDismiss;

			Content = new StackLayout {
				BackgroundColor = Color.FromHex("#030303"),
				Spacing = 0,
				Children = { tableView, button },
			};
		}

		async void OnDismiss(object sender, EventArgs e) {
			Button button = (Button)sender;
			button.IsEnabled = false;
			this.IsBusy = true;
			try {
				string username = usernameCell.Text;
				string email = emailCell.Text;
				string first = firstCell.Text;
				string last = lastCell.Text;

				if (string.IsNullOrWhiteSpace(username)
						|| string.IsNullOrWhiteSpace(email)) {
					this.IsBusy = false;
					await this.DisplayAlert("Missing Information",
							"You must enter values for the Username and Email.",
							"OK");
				} else {
					if (existingUser != null) {
						existingUser.username = username;
						existingUser.email = email;
						existingUser.first = first;
						existingUser.last = last;

						await controller.Update(existingUser);

						int pos = users.IndexOf(existingUser);
						users.RemoveAt(pos);
						users.Insert(pos, existingUser);
					} else {
						User user = await controller.Add(username, email, first, last);
						users.Add(user);
					}

					await Navigation.PopModalAsync();
				}

			}
			finally {
				this.IsBusy = false;
				button.IsEnabled = true;
			}
		}

	}
}
