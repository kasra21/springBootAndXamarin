using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace XamarinDemo.Data
{
	public class Controller
	{
		const string BaseUrl = "http://192.168.56.1:8080/api/";
		private string authorizationKey;

		public async Task<IEnumerable<User>> GetAllUsers() {

			HttpClient client = new HttpClient();

			string result = await client.GetStringAsync(BaseUrl + "getUsers");
			Object responseObj = JsonConvert.DeserializeObject(result);
			string actuallResult = result.Substring( result.IndexOf('['), result.IndexOf(']') - result.IndexOf('[') + 1 );

			return JsonConvert.DeserializeObject<IEnumerable<User>>(actuallResult);

		}

		public async Task<User> Add(string username, string email, string first, string last) {

			User user = new User() {
				username = username,
				email = email,
				first = first,
				last = last
			};

			HttpClient client = new HttpClient();
			var response = await client.PostAsync(BaseUrl + "addUser",
					new StringContent(
							JsonConvert.SerializeObject(user),
							Encoding.UTF8, "application/json"));

			string result = await response.Content.ReadAsStringAsync();
			Object responseObj = JsonConvert.DeserializeObject(result);
			string actuallResult = result.Substring(result.IndexOf('[') + 1, result.IndexOf(']') - (result.IndexOf('[') + 1) );

			return JsonConvert.DeserializeObject<User>(actuallResult);
		}

		public async Task Update(User user) {
			HttpClient client = new HttpClient();
			var response = await client.PostAsync(BaseUrl + "upadteUser",
			new StringContent(
				JsonConvert.SerializeObject(user),
				Encoding.UTF8, "application/json"));
		}

		public async Task Delete(string username) {

			HttpClient client = new HttpClient();
			var response = await client.PostAsync(BaseUrl + "deleteUser", 
			new StringContent( "{\"username\": \""+ username + "\"}" , Encoding.UTF8, "application/json"));
		}
	}
}

