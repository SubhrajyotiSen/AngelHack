//---------------------------------------------------------------------------
//
// <copyright file="BangaloreListPage.xaml.cs" company="Microsoft">
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
    public sealed partial class BangaloreListPage : Page
    {
	    public ListViewModel ViewModel { get; set; }
        public BangaloreListPage()
        {
			ViewModel = ViewModelFactory.NewList(new BangaloreSection());

            this.InitializeComponent();
			commandBar.DataContext = ViewModel;
			NavigationCacheMode = NavigationCacheMode.Enabled;
        }

        protected override async void OnNavigatedTo(NavigationEventArgs e)
        {
			ShellPage.Current.ShellControl.SelectItem("a64f8717-7eb3-4b2f-9e73-64dcd6dce97f");
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
