#include <iostream>

using namespace std;

int max(int a, int b)
{
    return (a > b) ? a : b;
}

int MaxSum(int arr[], int low, int mid, int high)
{
    int sum = 0;
    int leftSum = -1;
    for (int i = mid; i >= low; i--)
    {
        sum += arr[i];
        if (sum > leftSum)
            leftSum = sum;
    }

    sum = 0;
    int rightSum = -1;
    for (int i = mid + 1; i <= high; i++)
    {
        sum += arr[i];
        if (sum > rightSum)
            rightSum = sum;
    }

    return leftSum + rightSum;
}

int MaxSubSum(int arr[], int low, int high)
{
    int mid;
    if (low == high)
        return arr[low];

    mid = (low + high) / 2;

    return max(max(MaxSubSum(arr, low, mid), MaxSubSum(arr, mid + 1, high)),
               MaxSum(arr, low, mid, high));
}

int main()
{
    int n;
    cin >> n;

    int a[n];
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }

    cout << MaxSubSum(a, 0, n - 1);
    return 0;
}