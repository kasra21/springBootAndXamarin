using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace XamarinProject
{
	public partial class App : Application
	{
		public App() {

			MainPage = new NavigationPage
		(new MainPage()) {
				BarBackgroundColor = Color.FromHex("#000000"),
				BarTextColor = Color.FromHex("#FFFFFF"),
			};
			
		}

		protected override void OnStart() {
			// Handle when your app starts
		}

		protected override void OnSleep() {
			// Handle when your app sleeps
		}

		protected override void OnResume() {
			// Handle when your app resumes
		}
	}
}
