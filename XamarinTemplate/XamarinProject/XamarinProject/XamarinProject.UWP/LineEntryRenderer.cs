using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.UI;
using Windows.UI.Xaml.Media;
using Xamarin.Forms;
using Xamarin.Forms.Platform.UWP;
using XamarinProject;
using XamarinProject.UWP;

[assembly: ExportRenderer(typeof(LineEntry), typeof(LineEntryRenderer))]
namespace XamarinProject.UWP
{
	public class LineEntryRenderer:EntryRenderer
	{
		protected override void OnElementChanged(ElementChangedEventArgs<Entry> e) {
			base.OnElementChanged(e);

			if (Control != null) {
				// do whatever you want to the UITextField here!
				Control.Background = new SolidColorBrush(Colors.Green);
				Control.Foreground = new SolidColorBrush(Colors.Yellow);
			}
		}
	}
}
