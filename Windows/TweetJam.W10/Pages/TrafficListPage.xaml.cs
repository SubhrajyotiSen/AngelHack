//---------------------------------------------------------------------------
//
// <copyright file="TrafficListPage.xaml.cs" company="Microsoft">
//    Copyright (C) 2015 by Microsoft Corporation.  All rights reserved.
// </copyright>
//
// <createdOn>7/16/2016 11:50:45 AM</createdOn>
//
//---------------------------------------------------------------------------

using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Navigation;
using Windows.UI.Xaml;
using AppStudio.DataProviders.Twitter;
using TweetJam.Sections;
using TweetJam.ViewModels;
using AppStudio.Uwp;

namespace TweetJam.Pages
{
    public sealed partial class TrafficListPage : Page
    {
	    public ListViewModel ViewModel { get; set; }
        public TrafficListPage()
        {
			ViewModel = ViewModelFactory.NewList(new TrafficSection());

            this.InitializeComponent();
			commandBar.DataContext = ViewModel;
			NavigationCacheMode = NavigationCacheMode.Enabled;
        }

        protected override async void OnNavigatedTo(NavigationEventArgs e)
        {
			ShellPage.Current.ShellControl.SelectItem("328c2134-d031-46f6-b728-88cab19c8934");
			ShellPage.Current.ShellControl.SetCommandBar(commandBar);
			if (e.NavigationMode == NavigationMode.New)
            {			
				await this.ViewModel.LoadDataAsync();
                this.ScrollToTop();
			}			
            base.OnNavigatedTo(e);
        }

    }
}
