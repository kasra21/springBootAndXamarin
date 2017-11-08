using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using System.Collections.ObjectModel;
using XamarinProject.Models;
using XamarinProject.ViewModels;
using XamarinProject.Views;

namespace XamarinProject
{
	public partial class MainPage : ContentPage
	{
		readonly IList<User> users = new ObservableCollection<User>();
		readonly Controller controller = new Controller();

		public MainPage() {
			BindingContext = users;
			InitializeComponent();
		}

		async void OnSearchChange(object sender, EventArgs e) {

			this.IsBusy = true;
			SearchInput.IsEnabled = false;

			try {
				var userCollection = await controller.GetUser(SearchInput.Text);
				users.Clear();
				if (userCollection != null) {
					foreach (User user in userCollection) {
						if (users.All(b => b.username != user.username))
							users.Add(user);
					}
				}
			}
			finally {
				this.IsBusy = false;
				SearchInput.IsEnabled = true;
			}
		}

		async void OnRefresh(object sender, EventArgs e) {
			// Turn on network indicator
			this.IsBusy = true;

			try {
				var userCollection = await controller.GetAllUsers();

				foreach (User user in userCollection) {
					if (users.All(b => b.username != user.username))
						users.Add(user);
				}
			}
			finally {
				this.IsBusy = false;
			}
		}

		async void OnAddNewUser(object sender, EventArgs e) {
			await Navigation.PushModalAsync(
					new AddEditUserPage(controller, users));
		}

		async void OnEditUser(object sender, ItemTappedEventArgs e) {
			await Navigation.PushModalAsync(
					new AddEditUserPage(controller, users, (User)e.Item));
		}

		async void OnDeleteUser(object sender, EventArgs e) {
			MenuItem item = (MenuItem)sender;
			User user = item.CommandParameter as User;
			if (user != null) {
				if (await this.DisplayAlert("Delete User?",
						"Are you sure you want to delete the user '"
								+ user.username + "'?", "Yes", "Cancel") == true) {
					this.IsBusy = true;
					try {
						await controller.Delete(user.username);
						users.Remove(user);
					}
					finally {
						this.IsBusy = false;
					}

				}
			}
		}

	}
}
